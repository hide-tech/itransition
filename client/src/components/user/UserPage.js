import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'
import { Container } from 'semantic-ui-react'
import AuthContext from '../context/AuthContext'
import { serverApi } from '../server/ServerApi'
import { handleLogError } from '../server/ErrorHandler'

class UserPage extends Component {
  static contextType = AuthContext

  state = {
    isUser: true
  }

  componentDidMount() {
    const Auth = this.context
    const user = Auth.getUser()
    const isUser = user.role === 'USER'
    this.setState({ isUser })
  }

  handleInputChange = (e, { name, value }) => {
    this.setState({ [name]: value })
  }

  render() {
    
  }
}

export default UserPage