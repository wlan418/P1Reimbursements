import { useEffect, useState } from "react"
import { User } from "../Interface/Users"
import axios from "axios"
import { Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../GlobalData/store"

export const Users:React.FC = () => {
    const navigate = useNavigate()
    const [users,setUsers] = useState<User[]>([])

    useEffect(() => {
        getAllUsers()
    },[])

    const getAllUsers = async () => {
        try {
            const response = await axios.get("http://localhost:8080/users/getall")
            console.log(response.data)
            setUsers(response.data)
        } catch {
            alert("Something went wrong")
        }
    }

    const deleteUser = async (user:User) => {
        try {
            const response = await axios.post("http://localhost:8080/users/delete",user)
            alert("User with username " + user.username + " has been deleted")
            getAllUsers()
            const response2 = await axios.post("http://localhost:8080/reimbursements/deletebyuser",user.username)
        } catch {
            alert("Something went wrong")
        }
    }

    return (
        <>
            <h3>Users: </h3>
            <Table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map((user:User)=>(
                        <tr key={user.username}>
                            <td>{user.fName}</td>
                            <td>{user.lName}</td>
                            <td>{user.email}</td>
                            <td>{user.username}</td>
                            <td>{user.role}</td>
                            <td>
                                <button onClick={()=>deleteUser(user)}>Fire</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <button onClick={()=>navigate("/managerReimbursement")}>View Reimbursements</button>
        </>
    )
}