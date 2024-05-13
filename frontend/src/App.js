import Header from './component/Header'
import Dashboard from './component/Dashboard';
import NonProfitsPage from './component/NonProfitsPage';
import ViewEmail from './component/ViewEmail';
import SendEmail from './component/SendEmail';
import { Route, Routes } from 'react-router-dom';
import AddNonProfitPage from './component/AddNonProfitPage';

function App() {
  return (
    <div>
      <Header />
      <Routes>
        <Route path='/' element={<Dashboard />} />
        <Route path='/non-profits' element={<NonProfitsPage />} />
        <Route path='/view-email' element={<ViewEmail />} />
        <Route path='/send-email' element={<SendEmail />} />
        <Route path='/add-non-profit' element={<AddNonProfitPage />} />
      </Routes>
    </div>
  );
}

export default App;
