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

function getAllUsers() {
  return instance.get('/api/users', {
    headers: { 'Content-type': 'application/json' }
  })
}

function getUserById(userId) {
  return instance.get('/api/users/'+userId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function getUserByEmail(email) {
  return instance.get('/api/users/name/'+email, {
    headers: { 'Content-type': 'application/json' }
  })
}

function updateUser(user) {
  return instance.patch('/api/users', { user }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function changeUserSkin(skin, userId) {
  return instance.post('/api/users/'+userId+'/skin', {skin}, {
    headers: { 'Content-type': 'application/json' }
  })
}

function changeUserLanguage(language, userId) {
  return instance.post('/api/users/'+userId+'/language', {language}, {
    headers: { 'Content-type': 'application/json' }
  })
}

function banUserById(userId) {
  return instance.post('/api/admin/ban/'+userId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function unbanUserById(userId) {
  return instance.post('/api/admin/unban/'+userId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function setAdminUser(userId) {
  return instance.post('/api/admin/set-admin/'+userId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function setUserAdmin(userId) {
  return instance.post('/api/admin/set-user/'+userId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function searchItemsByName(itemName) {
  return instance.get('/api/items/search/name',{} ,{ params: {'nm':itemName }}, {
    headers: { 'Content-type': 'application/json' }
  })
}

function searchItemsByTag(tagName) {
  return instance.get('/api/items/search/tags',{} ,{ params: {'tg':tagName }}, {
    headers: { 'Content-type': 'application/json' }
  })
}

function getAllCollectionsByUserId(userId) {
  return instance.get('/api/users/'+userId+'/collections', {
    headers: { 'Content-type': 'application/json' }
  })
}

function getAllCollectionsById(userId, collectionId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function createNewCollection(userId, collection) {
  return instance.post('/api/users/'+userId+'/collections', {collection}, {
    headers: { 'Content-type': 'application/json' }
  })
}

function updateCollection(userId, collection) {
  return instance.patch('/api/users/'+userId+'/collections', {collection}, {
    headers: { 'Content-type': 'application/json' }
  })
}

function deleteCollection(userId, collectionId) {
  return instance.delete('/api/users/'+userId+'/collections'+collectionId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function getItemsByCollectionsId(userId, collectionId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId+'/items', {
    headers: { 'Content-type': 'application/json' }
  })
}

function getItemById(userId, collectionId, itemId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function createNewItem(userId, collectionId, item) {
  return instance.post('/api/users/'+userId+'/collections/'+collectionId+'/items', { item }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function updateItem(userId, collectionId, item) {
  return instance.patch('/api/users/'+userId+'/collections/'+collectionId+'/items', { item }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function deleteItemById(userId, collectionId, itemId) {
  return instance.delete('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId, {
    headers: { 'Content-type': 'application/json' }
  })
}

function getCommentsByItemId(userId, collectionId, itemId) {
  return instance.get('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId+'/comments', {
    headers: { 'Content-type': 'application/json' }
  })
}

function createNewComment(userId, collectionId, itemId, comment) {
  return instance.post('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId+'/comments', 
    { comment }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function putLikeOnComment(userId, collectionId, itemId, commentId) {
  return instance.post('/api/users/'+userId+'/collections/'+collectionId+'/items/'+itemId+'/comments/'+commentId, {
    headers: { 'Content-type': 'application/json' }
  })
}


// -- Axios

const instance = axios.create({
  baseURL: config.url.API_BASE_URL
})

// -- Helper functions

function basicAuth(user) {
  return `Basic ${user.authdata}`
}