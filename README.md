
# SoarLog

A comprehensive Android application for logging and tracking glider flights, built with Jetpack Compose and modern Android development practices.

## Features

### Flight Logging
- **Manual Flight Entry**: Log flights with detailed information including:
  - Aircraft registration
  - Glider type and specifications
  - Takeoff and landing locations
  - Launch method (winch, aerotow, etc.)
  - Flight duration
  - Pilot-in-command and P2 details
  - Flight notes and observations

### Flight Management
- **Flight List View**: Browse all logged flights with smart sorting options
  - Sort by date (latest first)
  - Sort by duration (longest first)
  - Sort by glider type


### OGN Integration
- **Live Flight Search**: Search for flights using Open Glider Network (OGN) data
- **Airfield-based Search**: Find flights by specific airfields and dates
- **Registration Filtering**: Filter OGN results by aircraft registration
- **Import Capability**: Convert OGN flight data to personal flight logs

### Statistics & Analytics
- **Flight Statistics**: Comprehensive flight analytics including:
  - Total flights logged
  - Total flight time
  - Average flight duration
  - Longest flight record
  - Most frequently flown aircraft


## Technology Stack

### Core Framework
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3 Design
- **Architecture**: MVVM (Model-View-ViewModel)
- **Minimum SDK**: Android 7.0 (API level 24)
- **Target SDK**: Android 14 (API level 34)

### Key Dependencies
- **Database**: Room with Flow-based reactive queries
- **Networking**: Retrofit with Moshi JSON parsing
- **Navigation**: Jetpack Navigation Compose
- **Coroutines**: Kotlin Coroutines for asynchronous operations
- **HTTP Logging**: OkHttp logging interceptor for debugging

### External APIs
- **Open Glider Network (OGN)**: Real-time glider flight tracking data


## Architecture Overview

### Data Layer
- **Room Database**: Local flight storage with reactive queries
- **Repository Pattern**: Centralized data access with caching
- **Network Layer**: Retrofit-based OGN API integration

### Presentation Layer
- **Compose UI**: Modern declarative UI with Material 3
- **ViewModels**: Lifecycle-aware data management
- **Navigation**: Type-safe navigation between screens

### Key Components
- **FlightRepository**: Manages local and remote flight data
- **FlightLogViewModel**: Handles UI state and business logic
- **Compose Screens**: Modular, reusable UI components
- **Database Entities**: Flight model with comprehensive fields

## Contributing

We welcome contributions! Here's how to get started:

### Development Setup
1. **Fork the Repository** on GitHub
2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Make Changes** following our coding standards
4. **Test Thoroughly** on multiple device sizes
5. **Submit Pull Request** with detailed description

### Code Style
- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Comment complex logic and business rules
- Maintain consistent formatting with ktlint

### Testing Guidelines
- Write unit tests for ViewModels and repositories
- Test UI components with Compose testing framework
- Verify database operations and data integrity
- Test network integration with mock responses

## Roadmap

### Possible Upcoming Features
- **Weather Integration**: Automatic weather data for flight dates
- **Logbook Export**: PDF/CSV export for official logbooks
- **Cloud Backup**: Secure cloud storage for flight data
- **Multi-User Support**: Shared aircraft and club features
- **Advanced Analytics**: Detailed performance and progress tracking

### Version History
- **v1.0** (Current): Core flight logging, OGN integration, statistics
- **v0.9**: Beta release with basic functionality
- **v0.5**: Alpha release for internal testing

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- **Open Glider Network (OGN)**: For providing free flight tracking data
- **Android Jetpack Team**: For excellent modern Android development tools
- **Gliding Community**: For feedback and feature suggestions
- **Material Design Team**: For comprehensive design guidelines

## Support

### Getting Help
- **Issues**: Report bugs on GitHub Issues

### FAQ

**Q: Can I use SoarLog for other aircraft types?**
A: Currently optimized for gliders, but can log any aircraft type.

**Q: Does SoarLog work offline?**
A: Yes, all logged flights are stored locally. OGN search requires internet.

**Q: Can I export my flight data?**
A: Export features are planned for a future release.

**Q: Is my flight data secure?**
A: All data is stored locally on your device. No data is transmitted without your permission.

---

**SoarLog** - Making flight logging simple, comprehensive, and enjoyable for glider pilots worldwide.
