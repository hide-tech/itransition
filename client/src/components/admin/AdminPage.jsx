import UserTable from "./UserTable";
import { useState } from "react";

const AdminPage = () => {

    const [ users, setUsers ] = useState

    function banUser( userId ) {

    }

    function unbanUser( userId ) {

    }

    function makeAdmin( userId ){
        
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