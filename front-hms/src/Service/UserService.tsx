import axiosInstance from '../Interceptor/AxiosInterceptor';

const registerUser = async (user:any) => {
  return axiosInstance.post(`/user/register`, user)
  .then (res => res.data)
  .catch(error => {throw error});
}

const loginUser = async (user:any) => {
  return axiosInstance.post(`/user/login`, user)
  .then (res => res.data)
  .catch(error => {throw error});
}


export { registerUser, loginUser };
