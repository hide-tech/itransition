import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "./context/useAuth";
import { serverApi } from "./server/ServerApi"
import { handleLogError } from "./server/ErrorHandler";

const Login = () => {
    const navigate = useNavigate()
    const location = useLocation()
    const { login, setFailed } = useAuth()

    const fromPage = location.state?.from?.pathname || "/"

    const handleSubmit = (event) => {
        event.preventDefault()

        const username = event.target.usname.value
        const password = event.target.pwd.value

        serverApi.getUserByEmail(username, password, username)
        .then(response => {
            if (response.status === '200'){
                const getUser = JSON.parse(response.data)
                login(getUser, () => navigate(fromPage, {replace: true}))
            }
        }).catch(error => {
            handleLogError(error)
            setFailed(true)
        })
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