import axios from "axios"
import { useState } from "react"
import { Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store, storeInfo } from "../GlobalData/store"

export const Register : React.FC = () => {
    const navigate = useNavigate()
    const [role,setRole] = useState(false)
    const updateRole = () => {setRole(!role)}
    const [formInfo,setFormInfo] = useState({
        fname:"",
        lname:"",
        email:"",
        uname:"",
        pwd:"",
        pwd2:""
    })
    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name
        const value = event.target.value
        setFormInfo((formInfo)=>({...formInfo,[name]:value}))
    }
    const verifyInfo = () => {
        if (!formInfo.fname||!formInfo.lname)
            alert("Empty first or last name")
        else if (!formInfo.email)
            alert("Blank email")
        else if (!formInfo.uname)
            alert("Username is empty")
        else if (!formInfo.pwd||!formInfo.pwd2)
            alert("One of the password fields is empty")
        else if (formInfo.pwd!==formInfo.pwd2)
            alert("Passwords do not match")
        else
            register()
    }
    const register = async () => {
        try {
            console.log("Registering")
            console.log(formInfo)
            const cred = {
                username:formInfo.uname,
                fName:formInfo.fname,
                lName:formInfo.lname,
                email:formInfo.email,
                password:formInfo.pwd,
                role:role?"Manager":"Employee"
               }
           const response = await axios.post("http://localhost:8080/auth/register",cred)
           alert("Welcome " + formInfo.fname + " " + formInfo.lname)
           storeInfo.credentials=cred
           console.log(storeInfo.credentials)
           navigate("/")
        } catch {
            alert("Username may have already existed")
        }
    }
    return (
        <>
            <h1>Register</h1>
            <div>
                <Form.Control type="text" placeholder="First Name" name="fname" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="text" placeholder="Last Name" name="lname" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="email" placeholder="Email" name="email" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="text" placeholder="Username" name="uname" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="password" placeholder="Password" name="pwd" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="password" placeholder="Verify" name="pwd2" onChange={storeValues}/>
            </div>
            <div>
                <input type="checkbox" id="role" checked={role} onChange={updateRole}/>
                <label htmlFor="role">Manager</label>
                <p>Current role: {role ? "Manager" : "Employee"}</p>
            </div>
            <button onClick={()=>navigate("/")}>Back to login</button>
            <button onClick={verifyInfo}>Create Account!</button>

            
        </>
    )
}