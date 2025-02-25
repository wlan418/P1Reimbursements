import axios from "axios"
import { useState } from "react"
import { Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store, storeInfo } from "../GlobalData/store"

export const Login : React.FC = () => {
    const navigate = useNavigate()
    const [loginInfo,setLoginInfo] = useState({
        username:"",
        password:""
    })
    const updateValues = (event:React.ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name
        const value = event.target.value

        setLoginInfo((loginInfo)=>({...loginInfo, [name]:value}))
    }
    const login = async () => {
        if (!loginInfo.username||!loginInfo.password)
            alert("Username and password required")
        else {
            try {
                const response = await axios.post("http://localhost:8080/auth/login",loginInfo)
                alert(loginInfo.username + " has logged in")
                //storeInfo.credentials = response.data
                //console.log(storeInfo.credentials)
                store.login = loginInfo
                const user = await axios.post("http://localhost:8080/users/finduser",loginInfo)
                storeInfo.credentials = user.data
                console.log(storeInfo.credentials)
                if (storeInfo.credentials.role==="Manager")
                    navigate("/managerReimbursement")
                else
                    navigate("/employeeReimbursement")
            } catch {
                alert("Incorrect username or password")          
            }
        }    
        
        
    }
    return (
        <>
            <h3>Welcome! Please sign in:</h3>
            <div>
                <Form.Control type="text" placeholder="username" name="username" onChange={updateValues}/>
            </div>
            <div>
                <Form.Control type="password" placeholder="password" name="password" onChange={updateValues}/>
            </div>
            <button onClick={login}>Login</button>
            <button onClick={() => navigate("/register")}>Create Account</button>
        </>
    )
}