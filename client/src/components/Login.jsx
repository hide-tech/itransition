import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "./context/useAuth";

const Login = () => {
    const navigate = useNavigate()
    const location = useLocation()
    const { login } = useAuth()

    const fromPage = location.state?.from?.pathname || "/"

    const handleSubmit = (event) => {
        event.preventDefault()

        const username = event.target.usname.value
        const password = event.target.pwd.value

        login({username, password}, () => navigate(fromPage, {replace: true}))
    }
    
    return ( 
        <div className="login">
            <h2>LoginPage!</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Name: <input name="usname" />
                </label>
                <label>
                    Password: <input name="pwd" />
                </label>
                <button type="submit">Login</button>
            </form>
        </div>
    );
}
 
export default Login;