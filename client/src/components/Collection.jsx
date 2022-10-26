import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAuth } from "./context/useAuth";
import { serverApi } from "./server/ServerApi";

const Collection = () => {

    const { collectionId } = useParams()
    const [ items, setItems ] = useState(null)
    const { user } = useAuth()
    let itemList

    useEffect(() => {
        serverApi.getItemsByCollectionsId(user, user.id, collectionId)
        .then(response => {
            setItems(JSON.parse(response.data))
        })
        .catch(error => {
            handleLogError(error)
        })
    }, [])

    function removeItem (itemId) {
        serverApi.deleteItemById(user, user.id, collectionId, itemId)
        .then(response => {
            if (response.status === "204"){
                let newItems = items.filter(it => it.id !== itemId)
                setItems(newItems)
            }
        })
        .catch(error => {
            handleLogError(error)
        })
    }

    function updateItem (item) {
        serverApi.updateItem(user, user.id, collectionId, item)
        .then(response => {
            if (response.status === "200"){
                let newItems = items.filter(it => it.id !== item.id)
                newItems.push(item)
                setItems(newItems)
            }
        })
        .catch(error => {
            handleLogError(error)
        })
    }

    function createItem (item) {
        serverApi.createNewItem(user, user.id, collectionId, item)
        .then(response => {
            if (response.status === "201"){
                let newItems = items
                newItems.push(item)
                setItems(newItems)
            }
        }).catch(error => {
            handleLogError(error)
        })
    }

    if (itemList.length === 0){
        itemList = <div key='no-items'>No items</div>
    } else {
        itemList = itemList.map( item => {
            return (
                <table className="items-table" key={item.id}>
                    <col>{item.id}</col>
                    <col><Link to={"/items/"+item.id}>{item.name}</Link></col>
                    <col>{item.tags}</col>
                    <col>{item.fields}</col>
                    <col><button onClick={removeItem(item.id)}>Remove item</button></col>
                    <col><Link to={'/item-form'} item={item} actionFunction={updateItem}>update item</Link></col>
                </table>
            )
        })
    }

    return ( 
        <div className="collection">
            <h2>Collection with id: {collectionId}. List of items:</h2>
            <table>
                <col>item id</col>
                <col>item name</col>
                <col>item tags</col>
                <col>item fields</col>
                <col>Delete item</col>
                <col>Update item</col>
            </table>
            {itemList}
            <Link to={'/item-form'} item={null} actionFunction={createItem}>Add new Item</Link>
        </div>
    );
}
 
export default Collection;