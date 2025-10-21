import { BrowserRouter, Route, Routes } from 'react-router-dom'
import AdminDashboard from "../Layout/AdminDashboard"
import Home from '../Components/Home'
import LoginPage from '../Pages/LoginPage'
import RegisterPage from '../Pages/RegisterPage'
import PublicRoute from './PublicRoute'
import ProtectedRoute from './ProtectedRoute'
import PatientDashboard from '../Layout/PatientDashboard'
import PatientProfilePage from '../Pages/Patient/PatientProfilePage'


const AppRoutes = () => {
  return (
    <BrowserRouter>
        <Routes>
          <Route path='/login' element={<PublicRoute><LoginPage /></PublicRoute> } />
          <Route path='/register' element={<PublicRoute><RegisterPage /></PublicRoute> } />
          <Route path="/"  element={<ProtectedRoute><AdminDashboard /></ProtectedRoute> } >
            <Route path='/dashboard' element={<Home/>} />
            <Route path='/pharmacy' element={<Home/>} />
            <Route path='/patient' element={<Home/>} />
            <Route path='/doctors' element={<Home/>} />
          </Route>


          <Route path="/patient"  element={<ProtectedRoute><PatientDashboard /></ProtectedRoute> } >
            <Route path='dashboard' element={<Home/>} />
            <Route path='profile' element={<PatientProfilePage />} />
            <Route path='appointments' element={<Home/>} />
            <Route path='book' element={<Home/>} />
          </Route>
        </Routes>

    </BrowserRouter>
  )
}

export default AppRoutes