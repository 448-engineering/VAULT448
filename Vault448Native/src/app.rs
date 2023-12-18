use crate::{
    fs_ops::{ConversionUtils, InternalStorageDetails},
    CurrentDirMetadata, VaultResult,
};
use camino::{Utf8Path, Utf8PathBuf};

#[derive(Debug, Default, uniffi::Record)]
pub struct DirOutcome {
    pub success: Option<CurrentDirMetadata>,
    pub failure: Vec<String>,
}

pub struct App {
    pub details: InternalStorageDetails,
}

impl App {
    pub async fn init(user_dir_path: &str) -> DirOutcome {
        let mut outcome = DirOutcome::default();

        match dir_meta::DirMetadata::new(user_dir_path)
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
}
