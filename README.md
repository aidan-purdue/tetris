# Tetris Game

A fully-featured Tetris game implementation in Java with enhanced features and custom scoring system.

## 🎮 Features

- **Classic Tetris Gameplay**: Complete implementation of the classic Tetris game mechanics
- **Custom Scoring System**: Advanced scoring with level progression and difficulty scaling
- **Music Integration**: Background music while playing (Tetris theme)
- **Hold Function**: Store a piece for later use (C key)
- **Pause Function**: Pause/resume the game (X key)
- **Restart Function**: Restart the game at any time (Z key)
- **Next Piece Preview**: See the next piece before it drops
- **Hold Piece Display**: Visual display of the held piece
- **Game Over Detection**: Proper game end functionality
- **Smooth Controls**: Responsive arrow key controls for movement and rotation

## 🎯 Game Controls

| Key | Action |
|-----|--------|
| ↑ | Rotate piece |
| ↓ | Soft drop (move down) |
| ← | Move left |
| → | Move right |
| Space | Hard drop (instant drop) |
| C | Hold piece |
| X | Pause/Resume |
| Z | Restart game |

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- BlueJ IDE (recommended) or any Java IDE

### Installation

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd GridMonsterAndTetris
   ```

2. Open the project in BlueJ or your preferred Java IDE

3. Compile all Java files

4. Run the `Tetris` class main method to start the game

### Running the Game

1. **Using BlueJ**:
   - Open `package.bluej` in BlueJ
   - Right-click on the `Tetris` class
   - Select "void main(String[] args)" and click "OK"

2. **Using Command Line**:
   ```bash
   javac *.java
   java Tetris
   ```

3. **Using IDE**:
   - Run the `main` method in the `Tetris` class

## 🏗️ Project Structure

```
GridMonsterAndTetris/
├── Tetris.java              # Main game class
├── Tetrad.java              # Tetris piece implementation
├── Block.java               # Individual block class
├── BlockDisplay.java        # GUI display for blocks
├── MyBoundedGrid.java       # Grid data structure
├── Location.java            # Position coordinates
├── ArrowListener.java       # Interface for key controls
├── MusicPlayer.java         # Audio playback
├── MusicOrganizer.java      # Music management
├── Track.java               # Audio track representation
├── TrackReader.java         # Audio file reading
├── GridMonster.java         # Testing utilities
├── BlockTest.java           # Unit tests
└── README.md               # This file
```

## 🎵 Audio Features

The game includes music integration with:
- Background Tetris theme music
- Music control (play/stop)
- Support for MP3 audio files

## 🧪 Testing

The project includes comprehensive testing:
- `GridMonster.java`: Tests grid and block functionality
- `BlockTest.java`: Unit tests for block operations

Run tests to verify the implementation:
```bash
java GridMonster
java BlockTest
```

## 🎨 Game Mechanics

### Scoring System
- Custom scoring algorithm with level progression
- Points awarded for line clears
- Difficulty increases with higher levels
- Faster piece dropping at higher levels

### Piece Types
The game includes all 7 classic Tetris pieces:
- **I-piece** (Cyan): Long vertical line
- **T-piece** (Magenta): T-shaped
- **O-piece** (Yellow): Square
- **L-piece** (Orange): L-shaped
- **J-piece** (Blue): Reverse L-shaped
- **S-piece** (Green): S-shaped
- **Z-piece** (Red): Z-shaped

### Grid System
- 20 rows × 10 columns playing field
- Collision detection
- Line clearing mechanics
- Piece placement validation

## 👨‍💻 Author

**Aidan Wang**
- Version: February 21, 2023
- Enhanced Tetris implementation with modern features

## 📚 Educational Context

This project was developed as part of a computer science curriculum, building upon the foundational classes from "Objects First with Java" by David J. Barnes and Michael Kölling.

## 🤝 Contributing

Feel free to contribute to this project by:
1. Forking the repository
2. Creating a feature branch
3. Making your changes
4. Submitting a pull request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🎮 Enjoy Playing!

Have fun playing this enhanced Tetris game! Try to beat your high score and master all the advanced features.
