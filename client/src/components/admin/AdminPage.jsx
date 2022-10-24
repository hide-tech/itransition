import UserTable from "./UserTable";
import { useState } from "react";
import { useAuth } from "../context/useAuth";
import { serverApi } from "../server/ServerApi"

const AdminPage = () => {

    const [ users, setUsers ] = useState
    const [ isFailed, setFailed ] = useState
    const { user } = useAuth

    function banUser( userId ) {
        serverApi.banUserById(user, userId)
        .then(
            console.log("user with id "+ userId + " has been banned")
        )
        .catch(error => {
            handleLogError(error)
            setFailed(true)
        })    
    }

    function unbanUser( userId ) {
        serverApi.unbanUserById(user, userId)
        .then(
            console.log("user with id "+ userId + " has been unlocked")
        )
        .catch(error => {
            handleLogError(error)
            setFailed(true)
        }) 
    }

    function makeAdmin( userId ){
        serverApi.setAdminUser(user, userId)
        .then(
            console.log("user with id "+ userId + " has became admin")
        )
        .catch(error => {
            handleLogError(error)
            setFailed(true)
        })
    }
    
    return ( 
        <div className="admin">
            <h2>AdminPage!</h2>
            <table>
                <col>User id</col>
                <col>user email</col>
                <col>user role</col>
                <col>user language</col>
                <col>user skin</col>
                <col>ban user</col>
                <col>unban user</col>
                <col>make admin</col>
            </table>
            <UserTable users={users} banUser={banUser()} unbanUser={unbanUser()} makeAdmin={makeAdmin()} />
        </div>
    );
}
 
export default AdminPage;