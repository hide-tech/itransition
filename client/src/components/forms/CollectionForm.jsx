import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "./context/useAuth";
import { serverApi } from "./server/ServerApi"
import { handleLogError } from "./server/ErrorHandler";

const CollectionForm = ({collection, actionFunction}) => {
    
    
    return ( 
        <h2>Edit your collection</h2>
    );
}
 
export default CollectionForm;