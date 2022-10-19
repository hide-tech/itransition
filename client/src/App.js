import React from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom'
import { AuthProvider } from './components/context/AuthContext'
import PrivateRoute from './components/server/PrivateRoute'
import Navbar from './components/server/Navbar'
import Home from './components/home/Home'
import Login from './components/home/Login'
import Signup from './components/home/Signup'
import AdminPage from './components/admin/AdminPage'
import UserPage from './components/user/UserPage'
import Google from './components/home/Google'

function App() {
  return (
    <AuthProvider>
      <Router>
        <Navbar />
        <Route path='/' exact component={Home} />
        <Route path='/login' component={Login} />
        <Route path='/signup' component={Signup} />
        <Route path='/google' component={Google} />
        <PrivateRoute path='/adminpage' component={AdminPage} />
        <PrivateRoute path='/userpage' component={UserPage} />
      </Router>
    </AuthProvider>
  )
}

export default App
