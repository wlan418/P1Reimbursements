import { useEffect, useState } from "react"
import { Reimbursement } from "../Interface/Reimbursements"
import axios from "axios"
import { Table } from "react-bootstrap"
import { storeInfo } from "../GlobalData/store"
import { useNavigate } from "react-router-dom"

export const ReimbursementEmployee:React.FC = () => {
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
                const response = await axios.post("http://localhost:8080/reimbursements/allpendingbyuser",storeInfo.credentials)
                console.log(response.data)
                setRmb(response.data)
            } else {
                const response = await axios.post("http://localhost:8080/reimbursements/allbyuser",storeInfo.credentials)
                console.log(response.data)
                setRmb(response.data)
            }
            console.log(storeInfo.credentials)
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
                        <th>Request</th>
                        <th>Notes</th>
                        <th>Status</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    {rmb.map((r:Reimbursement)=>(
                        <tr key={r.reimbursementId}>
                            <td>{r.reimbursementId}</td>
                            <td>{r.request}</td>
                            <td>{r.notes}</td>
                            <td>{r.status}</td>
                            <td>{r.amount}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <button onClick={changeTable}>{pending?"See pending":"See all"}</button>
            <button onClick={()=>navigate("/addReimbursement")}>Create Reimbursement</button>
            <button onClick={()=>navigate("/")}>Logout</button>
        </>
        
    )
}