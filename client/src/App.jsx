import React from 'react'
import { Routes, Route } from 'react-router-dom'
import { AuthProvider } from './components/context/AuthProvider'
import PrivateRoute from './components/server/PrivateRoute'
import Navbar from './components/Navbar'
import Home from './components/Home'
import Login from './components/Login'
import Signup from './components/Signup'
import AdminPage from './components/admin/AdminPage'
import UserPage from './components/user/UserPage'


function App() {
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
        </Route>
      </Routes>
    </AuthProvider>
  )
}

export default App
