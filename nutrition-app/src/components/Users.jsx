import React, { useState, useEffect } from 'react';
import { fetchData } from '../data/UserData'

const Users = () => {
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
  
    useEffect(() => {
      fetchData()
        .then(data => {
          setData(data);  // Handle your data here
          setLoading(false);
        })
        .catch(error => {
          setError(error);
          setLoading(false);
        });
    }, []); // Empty dependency array means this effect will only run once after the initial render
  
    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;
  
    return (
      <div>
        <h1>Data Loaded</h1>
        <pre>{JSON.stringify(data, null, 2)}</pre>
      </div>
    );
  };
  
  export default Users;