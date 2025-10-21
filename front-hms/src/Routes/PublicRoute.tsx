import { type JSX } from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router";
import { jwtDecode } from 'jwt-decode';


interface PublicRouteProps {
  children: JSX.Element;
}

const PublicRoute: React.FC<PublicRouteProps> = ({ children }) => {
  const token = useSelector((state: any) => state.jwt);
  if (token) {
    const user: any = jwtDecode(token);
    return <Navigate to={`/${user?.role?.toLowerCase()}/dashboard`} />;
  }

  return children;
};

export default PublicRoute;
