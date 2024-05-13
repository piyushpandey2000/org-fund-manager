import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import SearchableList from './SearchableList';
import { FaRegTrashAlt } from "react-icons/fa";

const NonProfitsPage = () => {
    const API_URL_GET_ALL = 'http://localhost:8080/nonprofit/getAll';
    const API_URL_REMOVE = 'http://localhost:8080/nonprofit/remove?id=';

    const [ nonProfitList, setNonProfitList ] = useState([]);
    const [ searchStr, setSearchStr ] = useState('');

    const fetchNonProfitList = async () => {
        try {
            const response = await fetch(API_URL_GET_ALL);
            const info = await response.json();
            setNonProfitList(info);
        } catch (e) {
            console.error(e.stack)
        }
    }

    useEffect(() => {
        fetchNonProfitList();
    }, []);

    const handleRemove = (e) => {
        const removeById = async (id) => {
            try {
                await fetch(API_URL_REMOVE + id,
                    {method: 'DELETE', body: {}}
                );
                fetchNonProfitList();
            } catch (e) {
                console.error(e.stack)
            }
        }

        removeById(e.target.id);
    }

    const tableColumns = [
        { name: 'ID', selector: row => row.id, sortable:true},
        { name: 'Name', selector: row => row.name, sortable:true},
        { name: 'Email', selector: row => row.email, sortable:true},
        { name: 'Address', selector: row => row.address, sortable:true},
        { name: 'Fund Transferred', selector: row => row.fundReceived, sortable:true},
        {
            cell:(row) => <FaRegTrashAlt onClick={handleRemove} id={row.id} />,
            ignoreRowClick: true,
            button: true
        }
    ]

    return (
        <SearchableList 
            columns={tableColumns}
            data={nonProfitList.filter((row) => row.name.toLowerCase().includes(searchStr))}
            setSearchStr={setSearchStr}
        />
    );
}

export default NonProfitsPage;