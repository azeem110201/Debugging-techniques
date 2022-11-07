import Adapter from 'enzyme-adapter-react-16';
import { mount, configure } from 'enzyme';
import { Draggable, Droppable } from "react-drag-and-drop";
import App from "./App";

configure({adapter: new Adapter()});
test('drag and drop', () => {
  const wrapper = mount(<App/>);

  expect(wrapper.find(Draggable)).toHaveLength(1);
  expect(wrapper.find(Droppable)).toHaveLength(1);
  const div = wrapper.find(".Droppable");
  expect(div.text()).toEqual("drop here");
  const onDrop = jest.fn();

  wrapper.find('.Droppable').simulate('drop')
  expect(onDrop).not.toHaveBeenCalled();
})