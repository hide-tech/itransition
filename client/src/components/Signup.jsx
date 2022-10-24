import { useNavigate, useLocation } from "react-router-dom";
import { handleLogError } from "./server/ErrorHandler";
import { serverApi } from "./server/ServerApi"
import { useAuth } from "./context/useAuth";

const Signup = () => {
    const navigate = useNavigate()
    const location = useLocation()
    const { setFailed } = useAuth()
    
    const handleSubmit = (event) => {
        event.preventDefault()

        const email = event.target.email.value
        const password = event.target.pwd.value

        serverApi.signup({email, password})
        .then(() => navigate('/'))
        .catch(error => {
            handleLogError(error)
            setFailed(true)
        })
    }

    return ( 
        <div className="sign-up">
            <h2>Sign-up page!</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Email: <input name="email" />
                </label>
                <label>
                    Password: <input name="pwd" />
                </label>
                <button type="submit">Register</button>
            </form>
        </div>
    );
}
 
export default Signup;