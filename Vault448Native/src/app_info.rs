use core::fmt;
use yaml_rust::{Yaml, YamlLoader};

const PUBSPEC: &str = include_str!("../../pubspec.yaml");

pub const PKG_VERSION: &str = env!("CARGO_PKG_VERSION");

pub fn version_checker(map: &Yaml) -> Option<&Yaml> {
    match map {
        Yaml::Hash(value) => value.get(&Yaml::String("version".to_owned())),
        _ => None,
    }
}

pub fn pretty_version() -> PubspecVersion {
    let docs = YamlLoader::load_from_str(PUBSPEC).unwrap(); // log_error

    let version = version_checker(&docs[0]);

    if let Some(version) = version {
        match version {
            Yaml::String(value) => PubspecVersion::Exists(value.to_owned()),
            _ => PubspecVersion::UnspecifiedVersion,
        }
    } else {
        PubspecVersion::UnspecifiedVersion
    }
}

#[derive(Debug, PartialEq, Eq, PartialOrd, Ord, Clone)]
pub enum PubspecVersion {
    Exists(String),
    UnspecifiedVersion,
}

impl fmt::Display for PubspecVersion {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(
            f,
            "{}",
            match self {
                Self::Exists(version) => version,
                Self::UnspecifiedVersion => "UNSPECIFIED_VERSION",
            }
        )
    }
}
