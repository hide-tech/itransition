import { useNavigate } from "react-router-dom";
import UploadPhoto from "./UploadPhoto";
import { useState } from "react";

const CollectionForm = ({changedCollection, actionFunction}) => {
    
    const [ collection, setCollection ] = useState(changedCollection)
    const navigate = useNavigate()

    const handleSubmit = (event) => {
        event.preventDefault()

        actionFunction(collection)
        navigate(-1)
    }

    const handleChangeName = (event) => {
        const newCollection = collection
        newCollection.name = event.target.name.value
        setCollection(newCollection)
    }

    const handleChangeDescription = (event) => {
        const newCollection = collection
        newCollection.description = event.target.desc.value
        setCollection(newCollection)
    }

    const handleChangeTheme = (event) => {
        const newCollection = collection
        newCollection.theme = event.target.theme.value
        setCollection(newCollection)
    }

    const setPhotoUri = (location) => {
        const newCollection = collection
        newCollection.photoUri = location
        setCollection(newCollection)
    }
    
    return ( 
        <div className="collection-form">
            <h2>Edit your collection</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Name: <input type="text" name="name" onChange={handleChangeName}>{collection.name}</input>
                </label>
                <br />
                <label>
                    Description: <input type="text" name="desc" onChange={handleChangeDescription}>{collection.description}</input>
                </label>
                <br />
                <label>
                    Theme: <input type="text" name="theme" onChange={handleChangeTheme}>{collection.theme}</input>
                </label>
                <br />
                <UploadPhoto setPhotoUri={setPhotoUri}/>
            </form>
        </div>
    );
}
 
export default CollectionForm;