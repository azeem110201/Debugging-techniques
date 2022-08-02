import './App.css';
import { Draggable, Droppable } from 'react-drag-and-drop'
import { useState } from 'react';

function App() {
  const [data, setData] = useState("drop here");
  
  return (
    <div className="App">
      <header className="App-header">
        <Droppable types={['test']} onDrop={(data) => {
            setData(data.test);
            console.log(data);
          }
          }>
          {data}
        </Droppable>
        <Draggable type="test" data="test-data">
          <div>
            drag this
          </div>
        </Draggable>
      </header>
    </div>
  );
}

export default App;
