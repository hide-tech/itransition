import UserTable from "./UserTable";
import { useState, useEffect } from "react";
import { useAuth } from "../context/useAuth";
import { serverApi } from "../server/ServerApi"
import Collections from "./Collections";

const AdminPage = () => {

    const [ users, setUsers ] = useState(null)
    const [ isFailed, setFailed ] = useState(false)
    const [ collections, setCollections ] = useState(null)
    const { user } = useAuth

    useEffect(() => {
        serverApi.getAllUsers(user)
        .then(response => {
            const usersFromServer = JSON.parse(response.data)
            setUsers(usersFromServer)
        }).catch(error => {
            handleLogError(error)
            setFailed(true)
        })


    }, [])

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

    function removeCollection(collectionId) {
        serverApi.deleteCollection(user, user.id, collectionId)
        .then(response => {
            if (response.status === '204'){
                const newCollections = collections.filter(col => col.id !== collectionId)
                setCollections(newCollections)
            }
        }).catch(error => {
            handleLogError(error)
            setFailed(true)
        })
    }
    
    return ( 
        <div className="admin">
            <h2>UserTable</h2>
            <table>
                <col>User id</col>
                <col>user email</col>
                <col>user role</col>
                <col>user language</col>
                <col>user skin</col>
                <col>ban/unban user</col>
                <col>make admin</col>
            </table>
            <UserTable users={users} banUser={banUser()} unbanUser={unbanUser()} makeAdmin={makeAdmin()} />
            <h2>Collection table</h2>
            <table>
                <col>collection id</col>
                <col>collection name</col>
                <col>collection description</col>
                <col>collection theme</col>
                <col>Photo</col>
                <col>Delete collection</col>
                <col>Update collection</col>
            </table>
            <Collections collections={collections} removeCollection={removeCollection} />
        </div>
    );
}
 
export default AdminPage;