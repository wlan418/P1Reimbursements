import { useEffect, useState } from "react"
import { Reimbursement } from "../Interface/Reimbursements"
import axios from "axios"
import { Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { storeInfo } from "../GlobalData/store"

export const ReimbursementManager:React.FC = () => {
    const navigate = useNavigate()
    const [rmb,setRmb] = useState<Reimbursement[]>([])
    const [pending,setPending] = useState(false)

    const changeTable = () => {
        setPending(!pending)
        getAllRmbs()
    }
    useEffect(()=>{
        getAllRmbs()
    },[])
    const getAllRmbs = async () => {
        try {
            if (pending) {
                const response = await axios.get("http://localhost:8080/reimbursements/pending")
                console.log(response.data)
                setRmb(response.data)
            } else {
                const response = await axios.get("http://localhost:8080/reimbursements/all")
                console.log(response.data)
                setRmb(response.data)
            }
            
        } catch {
            alert("Something went wrong")
        }
    }
    const approve = async (r:Reimbursement) => {
        try {
            const response = await axios.patch("http://localhost:8080/reimbursements/approve",r)
            alert("Reimbursement " + r.request + " has been approved")
            getAllRmbs()
        } catch {
            alert("Something went wrong")
        }
    }
    const deny = async (r:Reimbursement) => {
        try {
            const response = await axios.patch("http://localhost:8080/reimbursements/deny",r)
            alert("Reimbursement " + r.request + " has been denied")
            getAllRmbs()    
        } catch {
            alert("Something went wrong")
        }
    }
    const deleteRmb = async (r:Reimbursement) => {
        try {
            const response = await axios.post("http://localhost:8080/reimbursements/delete",r)
            alert("Reimbursement " + r.request + " has been deleted")
            getAllRmbs()    
        } catch {
            alert("Something went wrong")
        }
    }
    return (
        <>
            <h3>Logged in as {storeInfo.credentials.username}</h3>
            <h3>Reimbursements: </h3>
            <Table>
                <thead>
                    <tr>
                        <th>Reimbursement ID</th>
                        <th>Username</th>
                        <th>Request</th>
                        <th>Notes</th>
                        <th>Status</th>
                        <th>Amount</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    {rmb.map((r:Reimbursement)=>(
                        <tr key={r.reimbursementId}>
                            <td>{r.reimbursementId}</td>
                            <td>{r.username}</td>
                            <td>{r.request}</td>
                            <td>{r.notes}</td>
                            <td>{r.status}</td>
                            <td>{r.amount}</td>
                            <td>
                                <button onClick={()=>approve(r)}>Approve</button>
                                <button onClick={()=>deny(r)}>Deny</button>
                                <button onClick={()=>deleteRmb(r)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <button onClick={changeTable}>{pending?"See pending":"See all"}</button>
            <button onClick={()=>navigate("/users")}>View Users</button>
            <button onClick={()=>navigate("/")}>Logout</button>
        </>
        
    )
}