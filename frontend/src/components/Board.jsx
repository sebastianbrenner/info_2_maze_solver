import React from "react";
import Square from "./Square";

class Board extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      startClicked: false,
      stopClicked: false
    };
    this.maze = {
      maze: [],
      sizeX: 0,
      sizeY: 0,
      start: 0,
      end: 0
    };
  }

  squareCallback = (status, id) => {
    this.setState({ startClicked: false });
    this.setState({ stopClicked: false });
    this.maze.maze[id] = status;
    if (status === "START") {
      this.maze.start = id;
    }
    if (status === "END") {
      this.maze.end = id;
    }
    console.log("(#" + id + ": \t" + status + ")");
  };

  // Create (sizeX x sizeY) squares
  renderSquares = (sizeX, sizeY) => {
    var squares = [];
    var startClicked = this.state.startClicked;
    var stopClicked = this.state.stopClicked;

    if (this.maze.maze.length !== sizeX * sizeY) {
      this.maze.maze = [];
      for (var idx = 0; idx < sizeX * sizeY; ++idx) {
        this.maze.maze.push("EMPTY");
      }
    }
    var k = 0;
    for (var i = 0; i < sizeX; i++) {
      var tmp = [];
      for (var j = 0; j < sizeY; j++) {
        tmp.push(
          <Square
            key={k}
            parentCallback={this.squareCallback}
            stopClicked={stopClicked}
            startClicked={startClicked}
            squareID={k}
          />
        );
        // All squares are empty by default. If Square has another fieldType it will have been clicked and we can set its real status
        // via the squareCallback
        k = k + 1;
      }
      squares.push(
        <div key={i} className="board-row">
          {tmp}
        </div>
      );
    }
    this.maze.sizeX = sizeX;
    this.maze.sizeY = sizeY;
    return squares;
  };

  render() {
    return <div>{this.renderSquares(this.props.sizex, this.props.sizey)}</div>;
  }
}

export default Board;
