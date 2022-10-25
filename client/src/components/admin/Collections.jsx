import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/useAuth"
import { serverApi } from "../server/ServerApi"

function Collections({collections, removeCollection}) {

    let collectionList

    const navigate = useNavigate()
    const { user } = useAuth()

    function createCollection(collection) {
        serverApi.createNewCollection(user, user.id, collection)
        .then(response => {
            if (response.status === '201'){
                () => navigate(-1)
            }
        })
        .catch(error => {
            handleLogError(error)
            setFailed(true)
        })
    }

    function updateCollection(collection) {
        serverApi.updateCollection(user, user.id, collection)
        .then(response => {
            if (response.status === '200'){
                () => navigate(-1)
            }
        })
        .catch(error => {
            handleLogError(error)
            setFailed(true)
        })
    }

    if (collectionList.length === 0){
        collectionList = <div key='no-collection'>No collections</div>
    } else {
        collectionList = collections.map( collection => {
            return (
                <table className="collection-table" key={collection.id}>
                    <col>{collection.id}</col>
                    <col>{collection.name}</col>
                    <col>{collection.description}</col>
                    <col>{collection.theme}</col>
                    <col><img src={collection.photoUri} /></col>
                    <col><button onClick={removeCollection(collection.id)}>Remove collection</button></col>
                    <col><Link to={'/collection'} collection={collection} actionFunction={updateCollection}>update collection</Link></col>
                </table>
            )
        })
    }
    
    return ( 
        <div className="user-table">
            {collectionList}
            <Link to={'/collection'} collection={null} actionFunction={createCollection}>Add new collection</Link>
        </div>
    );
}
 
export default Collections;