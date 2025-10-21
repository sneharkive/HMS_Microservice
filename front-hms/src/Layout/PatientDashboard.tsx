import SideBar from '../Components/Patient/SideBar/SideBar'
import Header from '../Components/Header/Header'
import { Outlet } from 'react-router-dom'

const PatientDashboard = () => {
  return (
    <div className="flex">
      <SideBar />
      <div className="w-full flex flex-col">
        <Header />
        <Outlet />
      </div>
    </div>
  )
}

export default PatientDashboard