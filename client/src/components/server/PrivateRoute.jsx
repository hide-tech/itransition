import React from 'react'
import { useLocation, Navigate } from 'react-router-dom'
import { useAuth } from '../context/useAuth';

const PrivateRoute = ({ children }) => {

  const {user} = useAuth()
  const location = useLocation();

  if (!user){
    return <Navigate to="/login" state={{from: location}} />
  }

  return (
    children
  )
}

export default PrivateRoute