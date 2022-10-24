import axios from 'axios'
import { config } from '../../Url'

export const serverApi = {
  authenticate,
  signup,
  oauth,
  getAllUsers,
  getUserById,
  getUserByEmail,
  updateUser,
  changeUserSkin,
  changeUserLanguage,
  banUserById,
  unbanUserById,
  setAdminUser,
  setUserAdmin,
  searchItemsByName,
  searchItemsByTag,
  getAllCollectionsByUserId,
  getAllCollectionsById,
  createNewCollection,
  updateCollection,
  deleteCollection,
  getItemsByCollectionsId,
  getItemById,
  createNewItem,
  updateItem,
  deleteItemById,
  getCommentsByItemId,
  createNewComment,
  putLikeOnComment
}

function authenticate(username, password) {
  return instance.post('/login', { username, password }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function signup(user) {
  return instance.post('/api/users', { user }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function oauth() {
    return instance.get('/oauth2/authorization/google');
}

function getAllUsers(user) {
  return instance.get('/api/users', {
    headers: { 'Content-type': 'application/json',
      'Authorization': basicAuth(user.username, user.password) 
    }
  })
}

function getUserById(user, userId) {
  return instance.get('/api/users/'+userId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password)
   }
  })
}

function getUserByEmail(username, password, email) {
  return instance.get('/api/users/name/'+email, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(username, password) }
  })
}

function updateUser(currUser, user) {
  return instance.patch('/api/users', { user }, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function changeUserSkin(user, skin, userId) {
  return instance.post('/api/users/'+userId+'/skin', {skin}, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function changeUserLanguage(user, language, userId) {
  return instance.post('/api/users/'+userId+'/language', {language}, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function banUserById(user, userId) {
  return instance.post('/api/admin/ban/'+userId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function unbanUserById(user, userId) {
  return instance.post('/api/admin/unban/'+userId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function setAdminUser(user, userId) {
  return instance.post('/api/admin/set-admin/'+userId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function setUserAdmin(user, userId) {
  return instance.post('/api/admin/set-user/'+userId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function searchItemsByName(user, itemName) {
  return instance.get('/api/items/search/name',{} ,{ params: {'nm':itemName }}, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function searchItemsByTag(user, tagName) {
  return instance.get('/api/items/search/tags',{} ,{ params: {'tg':tagName }}, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function getAllCollectionsByUserId(user, userId) {
  return instance.get('/api/users/'+userId+'/collections', {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function getAllCollectionsById(user, userId, collectionId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function createNewCollection(user, userId, collection) {
  return instance.post('/api/users/'+userId+'/collections', {collection}, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function updateCollection(user, userId, collection) {
  return instance.patch('/api/users/'+userId+'/collections', {collection}, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function deleteCollection(user, userId, collectionId) {
  return instance.delete('/api/users/'+userId+'/collections'+collectionId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function getItemsByCollectionsId(user, userId, collectionId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId+'/items', {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function getItemById(user, userId, collectionId, itemId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function createNewItem(user, userId, collectionId, item) {
  return instance.post('/api/users/'+userId+'/collections/'+collectionId+'/items', { item }, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function updateItem(user, userId, collectionId, item) {
  return instance.patch('/api/users/'+userId+'/collections/'+collectionId+'/items', { item }, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function deleteItemById(user, userId, collectionId, itemId) {
  return instance.delete('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function getCommentsByItemId(user, userId, collectionId, itemId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId+'/comments', {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function createNewComment(user, userId, collectionId, itemId, comment) {
  return instance.post('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId+'/comments', 
    { comment }, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}

function putLikeOnComment(user, userId, collectionId, itemId, commentId) {
  return instance.post('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId+'/comments/'+commentId, {
    headers: { 'Content-type': 'application/json',
    'Authorization': basicAuth(user.username, user.password) }
  })
}


const instance = axios.create({
  baseURL: config.url.API_BASE_URL
})

function basicAuth(username, password){
  return 'Basic '+ base64_decode(username + ":"+ password);
}