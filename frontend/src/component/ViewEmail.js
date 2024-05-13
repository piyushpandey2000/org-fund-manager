import { useEffect, useState } from 'react';
import SearchableList from './SearchableList';

const ViewEmail = () => {
    const API_URL_GET_ALL = 'http://localhost:8080/email/getAll';

    const [ emailList, setEmailList ] = useState([]);
    const [ searchStr, setSearchStr ] = useState('');

    const fetchEmailList = async () => {
        try {
            const response = await fetch(API_URL_GET_ALL);
            const info = await response.json();
            console.log(JSON.stringify(info));
            setEmailList(info);
        } catch (e) {
            console.error(e.stack)
        }
    }

    useEffect(() => {
        fetchEmailList();
    }, []);

    const tableColumns = [
        { name: 'ID', selector: row => row.id, sortable:true},
        { name: 'Email', selector: row => row.content},
        { name: 'Non Profit', selector: row => row.nonProfit.name, sortable:true}
    ]

    return (
        <SearchableList 
            columns={tableColumns}
            data={emailList.filter((row) => row.nonProfit.name.toLowerCase().includes(searchStr))}
            setSearchStr={setSearchStr}
        />
    );
}

export default ViewEmail;