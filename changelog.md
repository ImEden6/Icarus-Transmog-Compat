# Changelog

## [1.0.1] - 2026-04-14

### Added
- **Estrogen Compatibility**: Moth Elytras (from the Estrogen mod) now correctly respect Transmog appearances.
- **Trinket Support**: Icarus wings equipped in Trinket slots can now be transmogged into Moth Elytras, and they will render correctly using Estrogen's custom model.
- **Improved Soft Dependencies**: Implemented a Mixin Config Plugin to safely handle Estrogen compatibility without requiring it to be installed.

### Fixed
- **Invisible Wings Bug**: Resolved an issue where Estrogen Moth Elytras were invisible in production environments due to a mapping mismatch (Mojang vs Intermediary).
- **Mod Collisions**: Renamed all Mixin methods with a unique prefix (`icarustransmogcompat$`) to prevent crashes/collisions with other mods like *Ambient Sounds*.
- **Build System**: Fixed `fabric.mod.json` property expansion in the build script.

### Changed
- **Version**: Bumped version to `1.0.1`.
