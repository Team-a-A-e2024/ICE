import React, { useState, useEffect } from 'react';
import { fetchData } from '../data/ProductData';
import styles from './Products.module.css'
import SearchBar from './Searchbar';


const Products = () => {
    const [product, setProduct] = useState('');
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetchData(product)
            .then(data => {
                setData(data);  // Handle your data here
                setLoading(false);
            })
            .catch(error => {
                setError(error);
                setLoading(false);
            });
    }, [product]); // Empty dependency array means this effect will only run once after the initial render

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;

    const listItems = data.map(
        product =>
            <tr key={product.id}>
                <td>{product.name}</td>
                <td>{product.weight}g/ml</td>
                <td>{product.calorie}kcal</td>
                <td>{product.carb}g</td>
                <td>{product.sugar}g</td>
                <td>{product.protein}g</td>
                <td>{product.fat}g</td>
            </tr>
    )

    function handleSearch() {
        const searchTerm = document.getElementById("searchTerm").value;     
        setProduct([searchTerm]);
    }

    return (
        <div>
            <SearchBar onSearch={handleSearch}></SearchBar>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Weight</th>
                    <th>Calories</th>
                    <th>Carbs</th>
                    <th>Hereof Sugar</th>
                    <th>Protein</th>
                    <th>Fat</th>
                </tr>
                </thead>
                <tbody>
                    {listItems}
                </tbody>
                
            </table>
        </div>
    );
};

export default Products;