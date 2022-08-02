import { fireEvent, render, screen } from '@testing-library/react';
import App from './App';

test('drag and drop an item', () => {
  render(<App />);
  const draggable = screen.queryAllByText(/drag this/)[0];
  const droppable = screen.queryAllByText(/drop here/i)[0];
  dragAndDropItem(draggable, droppable);
  const dropped = screen.queryAllByText("test-data");
  expect(dropped.length).toBe(1);
  expect(dropped[0]).toBeInTheDocument();
});


function dragAndDropItem(draggable, droppable) {
  fireEvent.dragStart(draggable);
  fireEvent.drop(droppable);
}