import { useState } from "react";
import { useNavigate } from "react-router-dom";

const ItemForm = ({changedItem, actionFunction}) => {
    
    const [ item, setItem ] = useState(changedItem)
    const navigate = useNavigate()

    let tagsTable
    let fieldsTable

    const handleSubmit = (event) => {
        event.preventDefault()

        actionFunction(item)
        navigate(-1)
    }

    const handleChangeName = (event) => {
        const newItem = item
        newItem.name = event.target.name.value
        setItem(newItem)
    }

    const handleChangeTags = (event) => {
        const newItem = item
        newItem.tags = event.target.tags.value
        setItem(newItem)
    }

    const handleChangeFields = (event) => {
        const newItem = item
        newItem.fields = event.target.fields.value
        setItem(newItem)
    }

    if (item.tags.length == 0){
        tagsTable = <div key='no-tags'>No Tags</div>
    } else {
        tagsTable = item.tags.map(tag => {
            return (
                <label>
                    Tags: <input type="text" name="tag" onChange={handleChangeTags}>{tag.name}</input>
                </label>
            )
        })
    }

    const handleAddNewTag = (event) => {
        
    }
    
    if (item.fields.length == 0){
        fieldsTable = <div key='no-fields'>No Fields</div>
    } else {
        fieldsTable = item.fields.map(field => {
            return (
                <div>Fields</div>
            )
        })
    }

    const handleAddNewField = (event) => {
        
    }
    
    return ( 
        <div className="item-form">
            <h2>Edit your item</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Name: <input type="text" name="name" onChange={handleChangeName}>{item.name}</input>
                </label>
                <br />
                {tagsTable}
                <button onClick={handleAddNewTag}>Add new tag</button>
                <br />
                {fieldsTable}
                <button onClick={handleAddNewField}>Add new field</button>
            </form>
        </div>
    );
}
 
export default ItemForm;