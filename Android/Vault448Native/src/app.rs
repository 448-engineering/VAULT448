use crate::{ConversionUtils, CurrentDirMetadata};
use camino::{Utf8Path, Utf8PathBuf};

pub struct App {
    user_dir: Utf8PathBuf,
    user_dir_metadata: CurrentDirMetadata,
}

impl App {
    pub fn new(user_dir_path: &str) -> Self {
        Self {
            user_dir: Utf8Path::new(user_dir_path).to_path_buf(),
            user_dir_metadata: CurrentDirMetadata::default(),
        }
    }

    pub async fn init(&mut self) -> DirOutcome {
        let mut outcome = DirOutcome::default();

        match dir_meta::DirMetadata::new(self.user_dir.as_ref())
            .dir_metadata()
            .await
        {
            Err(error) => outcome.failure.push(error.to_string()),
            Ok(all_dir_meta) => {
                outcome
                    .success
                    .replace(ConversionUtils::from_dir_metdata(&all_dir_meta));
            }
        }

        outcome
    }

    pub fn user_dir(&self) -> &Utf8Path {
        self.user_dir.as_ref()
    }

    pub fn user_dir_metadata(&self) -> &CurrentDirMetadata {
        &self.user_dir_metadata
    }
}

#[derive(Debug, Default, uniffi::Record)]
pub struct DirOutcome {
    pub success: Option<CurrentDirMetadata>,
    pub failure: Vec<String>,
}
