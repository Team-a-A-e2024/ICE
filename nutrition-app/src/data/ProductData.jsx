import axios from 'axios';

const baseURL = import.meta.env.VITE_API_URL;

export const fetchData = async (query) => {
    try {
        const response = await axios.get(`${baseURL}/api/products/search/${query}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching data: ', error);
    }
};