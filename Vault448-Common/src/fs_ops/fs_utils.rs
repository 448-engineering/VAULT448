use crate::VaultResult;

pub struct FsUtils;

impl FsUtils {
    pub async fn calc_size(path: &str) -> VaultResult<usize> {
        let get_dir_metadata = dir_meta::DirMetadata::new(path).dir_metadata().await?;

        Ok(get_dir_metadata.size())
    }
}
