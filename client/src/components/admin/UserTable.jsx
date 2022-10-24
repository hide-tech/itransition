function UserTable({users, banUser, unbanUser, makeAdmin}) {

    let userList

    if (userList.length === 0){
        userList = <div key='no-user'>No users</div>
    } else {
        userList = users.map( user => {
            return (
                <table className="user-table" key={user.id}>
                    <col>{user.id}</col>
                    <col>{user.email}</col>
                    <col>{user.role}</col>
                    <col>{user.language}</col>
                    <col>{user.skin}</col>
                    <col><button onClick={banUser(user.id)}>Ban user</button></col>
                    <col><button onClick={unbanUser(user.id)}>Unban user</button></col>
                    <col><button onClick={makeAdmin(user.id)}>Make admin</button></col>
                </table>
            )
        })
    }
    
    return ( 
        <div className="user-table">
            {userList}
        </div>
    );
}
 
export default UserTable;