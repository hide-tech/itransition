import React , {useState} from 'react'
import S3 from "react-aws-s3-typescript"

window.Buffer = window.Buffer || require("buffer").Buffer;

const UploadPhoto = ({ setPhotoUri }) => {

    const [selectedFile, setSelectedFile] = useState(null);

    const config = {
        bucketName: process.env.REACT_APP_BUCKET_NAME,
        region: process.env.REACT_APP_REGION,
        accessKeyId: process.env.REACT_APP_ACCESS,
        secretAccessKey: process.env.REACT_APP_SECRET,
    }

    const handleFileInput = (e) => {
        setSelectedFile(e.target.files[0]);
    }

    const uploadFile = async (file) => {
        const ReactS3Client = new S3(config);
        ReactS3Client
        .uploadFile(file, file.name)
        .then(data => setPhotoUri(data.location))
        .catch(err => console.error(err))
    }

    return <div>
        <div>Photo Upload</div>
        <input type="file" onChange={handleFileInput}/>
        <br></br>
        <button onClick={() => uploadFile(selectedFile)}> Upload photo</button>
    </div>
}

export default UploadPhoto;