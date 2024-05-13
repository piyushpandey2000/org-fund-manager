import DataTable from 'react-data-table-component';
import { Link } from 'react-router-dom';
import '../style/SearchableList.css'

const SearchableList = ({ columns, data, setSearchStr }) => {
    const handleSearch = (e) => {
        setSearchStr(e.target.value.toLowerCase());
    }

    return (
        <div className='searchable-list-main'>
            <div className='searchable-list-add'>
                <button>
                    <Link
                    
                        to='/add-non-profit'
                    >
                        + Add
                    </Link>
                </button>
            </div>
            <div className='searchable-list-search'>
                <input type='text' onChange={handleSearch} placeholder='Search name' />
            </div>
            <DataTable 
                columns={columns}
                data={data}
                fixedHeader
                pagination
            >
            </DataTable>
        </div>
    );
}

export default SearchableList;