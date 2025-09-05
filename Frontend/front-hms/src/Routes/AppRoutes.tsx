import { BrowserRouter, Route, Routes } from 'react-router-dom'
import AdminDashboard from "../Layout/AdminDashboard"
import Home from '../Components/Home'
import LoginPage from '../Pages/LoginPage'
import RegisterPage from '../Pages/RegisterPage'


const AppRoutes = () => {
  return (
    <BrowserRouter>
        <Routes>
          <Route path='/login' element={<LoginPage />} />
          <Route path='/register' element={<RegisterPage />} />
          <Route path="/"  element={<AdminDashboard />} >
            <Route path='/dashboard' element={<Home/>} />
            <Route path='/pharmacy' element={<Home/>} />
            <Route path='/patients' element={<Home/>} />
            <Route path='/doctors' element={<Home/>} />
            <Route path='/appointments' element={<Home/>} />
          </Route>
        </Routes>

    </BrowserRouter>
  )
}

export default AppRoutes