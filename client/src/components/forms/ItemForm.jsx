import { useState } from "react";
import { useNavigate } from "react-router-dom";

const ItemForm = ({changedItem, actionFunction}) => {
    
    const [ item, setItem ] = useState(changedItem)
    const [ tags, setTags ] = useState(changedItem.tags)
    const [ fields, setFields ] = useState(changedItem.fields)
    const navigate = useNavigate()

    let tagsTable
    let fieldsTable

    const handleSubmit = (event) => {
        event.preventDefault()
        const newItem = item
        newItem.tags = tags
        newItem.fields = fields
        setItem(newItem)
        actionFunction(item)
        navigate(-1)
    }

    const handleChangeName = (event) => {
        const newItem = item
        newItem.name = event.target.name.value
        setItem(newItem)
    }

    const handleChangeTags = (index, e) => {
        const tagname = e.target.tag.value
        const tagList = tags
        const tagEdit = tagList[index]
        tagEdit.name = tagname
        tagList[index] = tagEdit
        setTags(tagList)
    }

    const handleChangeFieldKey = (key, e) => {
        const fieldKey = e.target.fieldK.value
        const fieldz = fields
        fieldz[fieldKey] = fieldz[key]
        delete fieldz[key]
        setFields(fieldz)
    }

    const handleChangeFieldValue = (key, e) => {
        const fieldValue = e.target.fieldV.value
        const fieldz = fields
        fieldz[key] = fieldValue
        setFields(fieldz)
    }

    if (tags.length == 0){
        tagsTable = <div key='no-tags'>No Tags</div>
    } else {
        tagsTable = tags.map((tag, index) => {
            return (
                <>
                    <label>
                        Tags: <input type="text" name="tag" onChange={e => handleChangeTags(index)}>{tag.name}</input>
                        <button type="button" onClick={e => handleDeleteTag(index)}>Delete tag</button>
                    </label> 
                </>
            )
        })
    }

    const handleAddNewTag = (event) => {
        const newTags = tags
        newTags.push({ 'name':'' })
        setTags(newTags)
    }

    const handleDeleteTag = (index, e) => {
        const elem = tags[index]
        const newTags = tags.filter(el => el !== elem)
        setTags(newTags)
    }
    
    if (fields.length == 0){
        fieldsTable = <div key='no-fields'>No Fields</div>
    } else {
        fieldsTable = Object.entries(fields).map((fieldKey, fieldValue) => {
            return (
                <>
                    <label>
                        <input type='text' name="fieldK" onChange={e => handleChangeFieldKey(fieldKey)}>{fieldKey}</input>: 
                        <input type="text" name="fieldV" onChange={e => handleChangeFieldValue(fieldKey)}>{fieldValue}</input>
                        <button type="button" onClick={e => handleDeleteField(fieldKey)}>Delete field</button>
                    </label> 
                </>
            )
        })
    }

    const handleAddNewField = (event) => {
        const newFields = fields
        newFields[''] = ''
        setFields(newFields)
    }

    const handleDeleteField = (key, e) => {
        const newFields = fields
        delete newFields[key]
        setFields(newFields)
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
                <button type="button" onClick={handleAddNewTag}>Add new tag</button>
                <br />
                {fieldsTable}
                <button type="button" onClick={handleAddNewField}>Add new field</button>
            </form>
        </div>
    );
}
 
export default ItemForm;