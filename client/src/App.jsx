import React from 'react'
import { Routes, Route, useParams } from 'react-router-dom'
import { AuthProvider } from './components/context/AuthProvider'
import PrivateRoute from './components/server/PrivateRoute'
import Navbar from './components/Navbar'
import Home from './components/Home'
import Login from './components/Login'
import Signup from './components/Signup'
import AdminPage from './components/admin/AdminPage'
import UserPage from './components/user/UserPage'
import CollectionForm from './components/forms/CollectionForm'
import ItemForm from './components/forms/ItemForm'
import Collection from './components/Collection'

function App() {

  const { collectionId } = useParams()

  return (
    <AuthProvider>
      <Routes>
        <Route path={'/'} element={<Navbar />}>
          <Route index element={<Home />} />
          <Route path={'login'} element={<Login />} />
          <Route path={'signup'} element={<Signup />} />
          <Route path={'admin'} element={
            <PrivateRoute>
              <AdminPage />
            </PrivateRoute>
          } />
          <Route path={'user'} element={
            <PrivateRoute>
              <UserPage />
            </PrivateRoute>
          } />
          <Route path={'collection-form'} element={<CollectionForm />} />
          <Route path={'item-form'} element={<ItemForm />} />
          <Route path={'collections/:collectionId'} element={<Collection />} />
        </Route>
      </Routes>
    </AuthProvider>
  )
}

export default App
