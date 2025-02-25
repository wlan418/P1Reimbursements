import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Login } from './Components/Login'
import { Register } from './Components/Register'
import { Users } from './Components/Users'
import { ReimbursementManager } from './Components/ReimbursementManager'
import { AddReimbursement } from './Components/AddReimbursement'
import { ReimbursementEmployee } from './Components/ReimbursementEmployee'

function App() {


  return (
    <BrowserRouter>
      <Routes>
        <Route path='' element={<Login/>}/>
        <Route path='register' element={<Register/>}/>
        <Route path='users' element={<Users/>}/>
        <Route path='managerReimbursement' element={<ReimbursementManager/>}/>
        <Route path='addReimbursement' element={<AddReimbursement/>}/>
        <Route path='employeeReimbursement' element={<ReimbursementEmployee/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
