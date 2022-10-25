import React from 'react'
import { NavLink, Outlet, useNavigate } from 'react-router-dom'
import { useAuth } from './context/useAuth'

function Navbar() {
    const { user, logout } = useAuth()
    const navigate = useNavigate()

    const enterMenuStyle = () => {
      return user ? { "display": "none" } : { "display": "block" }
    }
    
    const logoutMenuStyle = () => {
      return user ? { "display": "block" } : { "display": "none" }
    }
    
    const adminPageStyle = () => {
      const usr = user
      return usr && usr.role === 'ROLE_ADMIN' ? { "display": "block" } : { "display": "none" }
    }
    
    const userPageStyle = () => {
      const usr = user
      return usr && usr.role === 'ROLE_USER' ? { "display": "block" } : { "display": "none" }
    }

  return (
    <>
      <header>
        <NavLink to={"/"}>Home</NavLink>
        <NavLink to={"/login"} style={enterMenuStyle()}>Login</NavLink>
        <NavLink to={"/signup"} style={enterMenuStyle()}>Signup</NavLink>
        <div style={logoutMenuStyle()}> Hello ${user?.username}</div>
        <NavLink to={"/"} style={logoutMenuStyle()} onClick={()=>logout(()=>navigate('/'))}>Logout</NavLink>
        <NavLink to={"/user"} style={userPageStyle()}>User Page</NavLink>
        <NavLink to={"/admin"} style={adminPageStyle()}>Admin Page</NavLink>
      </header>

      <Outlet />
    </>
  )
}

export default Navbar