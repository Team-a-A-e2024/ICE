import axios from 'axios';

const baseURL = import.meta.env.VITE_API_URL;

export const fetchData = async () => {
    try {
        const response = await axios.get(`${baseURL}/api/users`);
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
    }
};