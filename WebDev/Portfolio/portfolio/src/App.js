import Header from './Components/Header'
import BioTile from './Components/BioTile'
import ProjectTile from './Components/ProjectTile'

function App() {
  return (
    <div className="app">
      <Header />
      <div className="main">
        <BioTile />
        <ProjectTile />
      </div>
    </div>
  );
}

export default App;
