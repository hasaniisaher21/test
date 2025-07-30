
import { Routes, Route, Navigate } from 'react-router-dom';
import VismeLogin from './components/VismeLogin';
import TeacherLanding from './components/TeacherLanding';
import StudentLanding from './components/StudentLanding';

function App() {
  return (
    <Routes>
      {/* Route for the login page */}
      <Route path="/" element={<VismeLogin />} />

      {/* Route for the teacher's landing page */}
      <Route path="/a" element={<TeacherLanding />} />

      {/* Route for the student's landing page */}
      <Route path="/b" element={<StudentLanding />} />

      {/* Any other path will redirect back to the login page */}
      <Route path="*" element={<Navigate to="/" />} />
    </Routes>
  );
}

export default App;
