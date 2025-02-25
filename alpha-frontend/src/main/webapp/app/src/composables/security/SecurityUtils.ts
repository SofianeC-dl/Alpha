import jwt_decode from 'jwt-decode';

export const isAdmin = (): boolean => {
  const token = localStorage.getItem('token');
  if (token) {
    const decoded = jwt_decode(token);
    const userRole = decoded.role;

    return userRole === 'ADMIN';
  }
}
