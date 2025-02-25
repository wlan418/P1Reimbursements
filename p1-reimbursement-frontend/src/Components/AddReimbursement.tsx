import axios from "axios"
import { useState } from "react"
import { Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store, storeInfo } from "../GlobalData/store"

export const AddReimbursement:React.FC = () => {
    const navigate = useNavigate()
    const [amount,setAmount] = useState(0)
    const [formInfo,setFormInfo] = useState({
        request:"",
        notes:"",
        amount:0
    })
    const storeValues = (event:React.ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name
        const value = event.target.value
        setFormInfo((formInfo)=>({...formInfo,[name]:value}))
    }
    const storeNumber = (event:React.ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value
        const parsed = parseFloat(value)
        setAmount(parsed)
    }
    const verifyInfo = () => {
        if (!formInfo.request)
            alert("Request required")
        else if (amount<=0)
            alert("Amount cannot be negative or zero")
        else
            register()
    }
    const register = async () => {
        try {
            const response = await axios.post("http://localhost:8080/reimbursements/add",{
                username:store.login.username,
                request:formInfo.request,
                notes:formInfo.notes,
                amount:'$'+amount.toFixed(2)
            }).then(()=>{
                alert("Reimbursement added")
            })
           console.log(response)
           navigate("/employeeReimbursement")
        } catch {
            alert("Unable to add reimbursement")
        }
    }
    return (
        <>
            <h1>Add Reimbursement</h1>
            <div>
                <Form.Control type="text" placeholder="Request" name="request" onChange={storeValues}/>
            </div>
            <div>
                <Form.Control type="text" placeholder="Notes (optional)" name="notes" onChange={storeValues}/>
            </div>
            <div>
                <input type="number" placeholder="Amount" name="amount" onChange={storeNumber}/>
            </div>
            <button onClick={()=>navigate("/employeeReimbursement")}>Back</button>
            <button onClick={verifyInfo}>Add</button>
            
        </>
    )
}