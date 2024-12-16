import React, { useState } from 'react';
import styles from './Searchbar.module.css'

function SearchBar({ onSearch }) {
  const [searchTerm, setSearchTerm] = useState('');

  const handleChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault(); // Prevent the form from reloading the page
    onSearch(searchTerm);  // Prop function to handle the search logic
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        id="searchTerm"
        placeholder="Search..."
        value={searchTerm}
        onChange={handleChange}
      />
      <button type="submit">Search</button>
    </form>
  );
}

export default SearchBar;
